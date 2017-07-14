//
//  JWTHandler.swift
//  assign-app
//
//  Created by Rob Van Gastel on 19/05/2017.
//  Copyright © 2017 Assign. All rights reserved.
//

import Foundation
import Alamofire

/// Manages the JWT tokens authentication on the API requests.
class JWTHandler: RequestAdapter, RequestRetrier {
    private typealias RefreshCompletion = (_ succeeded: Bool, _ accessToken: String?) -> Void

    private let sessionManager: SessionManager = {
        let configuration = URLSessionConfiguration.default
        configuration.httpAdditionalHeaders = SessionManager.defaultHTTPHeaders

        return SessionManager(configuration: configuration)
    }()

    private let lock = NSLock()

    private var accessToken: String

    private var isRefreshing = false
    private var requestsToRetry: [RequestRetryCompletion] = []

    // MARK: - Initialization

    public init() {
        self.accessToken = Storage.getToken()
    }

    // MARK: - RequestAdapter

    func adapt(_ urlRequest: URLRequest) throws -> URLRequest {
        // Adds Token to request
        if let urlString = urlRequest.url?.absoluteString, urlString.hasPrefix(Storage.getURL()) {
            var urlRequest = urlRequest
            urlRequest.setValue("Bearer " + accessToken, forHTTPHeaderField: "Authorization")
            return urlRequest
        }

        return urlRequest
    }

    // MARK: - RequestRetrier

    func should(_ manager: SessionManager, retry request: Request, with error: Error, completion: @escaping RequestRetryCompletion) {
        lock.lock() ; defer { lock.unlock() }

        // Manages the retries for an API call if the token is expired.
        if let response = request.task?.response as? HTTPURLResponse, response.statusCode == 401 {
            requestsToRetry.append(completion)

            // When the Handler isnt already refreshing the tokens, make
            // a refesh call.
            if !isRefreshing {
                refreshTokens { [weak self] succeeded, accessToken in
                    guard let strongSelf = self else { return }

                    strongSelf.lock.lock() ; defer { strongSelf.lock.unlock() }

                    if let accessToken = accessToken {
                        strongSelf.accessToken = accessToken
                    }

                    strongSelf.requestsToRetry.forEach { $0(succeeded, 0) }
                    strongSelf.requestsToRetry.removeAll()
                }
            }
        } else {
            completion(false, 0)
        }
    }

    // MARK: - Private - Refresh Tokens

    private func refreshTokens(completion: @escaping RefreshCompletion) {
        // When already refreshing return.
        guard !isRefreshing else { return }

        // When not already refreshing make the auth call.
        isRefreshing = true

        let urlString = "\(Storage.getURL())/auth"

        // Setting the parameters from the Storage.
        let parameters: Parameters = [
            "email": Storage.getCredentials().email!,
            "password": Storage.getCredentials().password!
        ]

        // Request API call for the token.
        sessionManager.request(urlString, method: .post, parameters: parameters, encoding: URLEncoding.queryString)
            .responseJSON { [weak self] response in
                guard let strongSelf = self else { return }

                // Parsing the Token from the response.
                if
                    let json = response.result.value as? [String: Any],
                    let accessToken = json["id_token"] as? String
                {
                    Storage.setToken(token: accessToken)
                    completion(true, accessToken)
                } else {
                    completion(false, nil)
                }

                strongSelf.isRefreshing = false
        }
    }
}
