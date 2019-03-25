//package nl.sean.dea.resource;
//
//import nl.sean.dea.dto.ErrorDTO;
//import nl.sean.dea.dto.TokenDTO;
//import nl.sean.dea.dto.UserDTO;
//import nl.sean.dea.resource.LoginResource;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import javax.ws.rs.core.Response;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class LoginResourceTest {
//    private LoginResource sut;
//
//    @BeforeEach
//    void setUp() {
//        sut = new LoginResource();
//    }
//
//    @Test
//    void loginSuccess() {
//        UserDTO user = new UserDTO("Sean", "test");
//        Response actualResult = sut.logUserIn(user);
//
//        assertEquals(Response.Status.OK.getStatusCode(), actualResult.getStatus());
//
//        TokenDTO tokenDTO = (TokenDTO) actualResult.getEntity();
//        assertEquals("Sean", tokenDTO.getUser());
//        assertEquals("1234", tokenDTO.getToken());
//    }
//
//    @Test
//    void loginFail() {
//        UserDTO user = new UserDTO("Sean", "wrongPassword");
//        Response actualResult = sut.logUserIn(user);
//
//        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResult.getStatus());
//
//        ErrorDTO errorDTO = (ErrorDTO) actualResult.getEntity();
//        assertEquals("Wrong user/password combination.", errorDTO.getMessage());
//    }
//}
