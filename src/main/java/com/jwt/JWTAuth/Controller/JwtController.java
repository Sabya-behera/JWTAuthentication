package com.jwt.JWTAuth.Controller;


import com.jwt.JWTAuth.Helper.JwtHelper;
import com.jwt.JWTAuth.Model.JwtRequest;
import com.jwt.JWTAuth.Model.JwtResponse;
import com.jwt.JWTAuth.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtHelper jwtHelper;

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
       System.out.println(jwtRequest);
       try {
          this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
       }catch (UsernameNotFoundException e)
       {
           e.printStackTrace();
           throw new Exception("Bad credentials");
       }catch (BadCredentialsException e)
       {
           e.printStackTrace();
           throw new Exception("Bad Credentials");
       }
       //fine area...
        UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
      String token= this.jwtHelper.generateToken(userDetails);
      System.out.println("JWT "+token);

      //{"token":"value"}
        return ResponseEntity.ok(new JwtResponse("token"));

    }
}
