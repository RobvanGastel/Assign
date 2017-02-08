//
//  JSONDecodable.swift
//  assign-app
//
//  Created by Rob Van Gastel on 08/02/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation

public protocol JSONDecodable {
    
    init(decoder: JSONDecoder) throws
    
}

public enum JSONDecoderError: Error {
    case invalidData
    case keyNotFound(String)
    case keyPathNotFound(String)
}

public struct JSONDecoder {
    
    typealias JSON = [String: AnyObject]
    
    // MARK: - Properties
    private let JSONData: JSON
    
    // MARK: - Static Methods
    public static func decode<T: JSONDecodable>(data: Data) throws -> T {
        let decoder = try JSONDecoder(data: data)
        return try T(decoder: decoder)
    }
    
    // MARK: - Initialization
    public init(data: Data) throws {
        if let JSONData = try JSONSerialization.jsonObject(with: data, options: []) as? JSON {
            self.JSONData = JSONData
        } else {
            throw JSONDecoderError.invalidData
        }
    }
    
    // MARK: -
    private init(JSONData: JSON) {
        self.JSONData = JSONData
    }
    
    // MARK: - Public Interface
    func decode<T>(key: String) throws -> T {
        if key.contains(".") {
            return try value(forKeyPath: key)
        }
        
        guard let value: T = try? value(forKey: key) else { throw JSONDecoderError.keyNotFound(key) }
        return value
    }
    
    func decode<T: JSONDecodable>(key: String) throws -> [T] {
        if key.contains(".") {
            return try value(forKeyPath: key)
        }
        
        guard let value: [T] = try? value(forKey: key) else { throw JSONDecoderError.keyNotFound(key) }
        return value
    }
    
    // MARK: - Private Interface
    private func value<T>(forKey key: String) throws -> T {
        guard let value = JSONData[key] as? T else { throw JSONDecoderError.keyNotFound(key) }
        return value
    }
    
    private func value<T: JSONDecodable>(forKey key: String) throws -> [T] {
        if let value = JSONData[key] as? [JSON] {
            return try value.map({ (partial) -> T in
                let decoder = JSONDecoder(JSONData: partial)
                return try T(decoder: decoder)
            })
        }
        
        throw JSONDecoderError.keyNotFound(key)
    }
    
    // MARK: -
    private func value<T>(forKeyPath keyPath: String) throws -> T {
        var partial = JSONData
        let keys = keyPath.components(separatedBy: ".")
        
        for i in 0..<keys.count {
            if i < keys.count - 1 {
                if let partialJSONData = JSONData[keys[i]] as? JSON {
                    partial = partialJSONData
                } else {
                    throw JSONDecoderError.invalidData
                }
                
            } else {
                return try JSONDecoder(JSONData: partial).value(forKey: keys[i])
            }
        }
        
        throw JSONDecoderError.keyPathNotFound(keyPath)
    }
    
    private func value<T: JSONDecodable>(forKeyPath keyPath: String) throws -> [T] {
        var partial = JSONData
        let keys = keyPath.components(separatedBy: ".")
        
        for i in 0..<keys.count {
            if i < keys.count - 1 {
                if let partialJSONData = JSONData[keys[i]] as? JSON {
                    partial = partialJSONData
                } else {
                    throw JSONDecoderError.invalidData
                }
                
            } else {
                return try JSONDecoder(JSONData: partial).value(forKey: keys[i])
            }
        }
        
        throw JSONDecoderError.keyPathNotFound(keyPath)
    }
}
