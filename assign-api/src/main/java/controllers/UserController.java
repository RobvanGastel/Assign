package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Rob
 */
@RestController
class UserController{
 
//   @Autowired
//   IFooService service;
 
    @RequestMapping("/")
    public String greeting() {
        return "Hello world!";
    }
   
//    @RequestMapping( method = RequestMethod.GET )
//    @ResponseBody
//    public List< Foo > findAll(){
//       return service.findAll();
//    }
//   @RequestMapping( value = "/{id}", method = RequestMethod.GET )
//   @ResponseBody
//   public Foo findOne( @PathVariable( "id" ) Long id ){
//      return RestPreconditions.checkFound( service.findOne( id ) );
//   }
// 
//   @RequestMapping( method = RequestMethod.POST )
//   @ResponseStatus( HttpStatus.CREATED )
//   @ResponseBody
//   public Long create( @RequestBody Foo resource ){
//      Preconditions.checkNotNull( resource );
//      return service.create( resource );
//   }
// 
//   @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
//   @ResponseStatus( HttpStatus.OK )
//   public void update( @PathVariable( "id" ) Long id, @RequestBody Foo resource ){
//      Preconditions.checkNotNull( resource );
//      RestPreconditions.checkNotNull( service.getById( resource.getId() ) );
//      service.update( resource );
//   }
// 
//   @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
//   @ResponseStatus( HttpStatus.OK )
//   public void delete( @PathVariable( "id" ) Long id ){
//      service.deleteById( id );
//   }
 
}