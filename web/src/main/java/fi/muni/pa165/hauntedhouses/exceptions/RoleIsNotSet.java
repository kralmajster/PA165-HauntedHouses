package fi.muni.pa165.hauntedhouses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Klara Kufova, 410091
 */

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="The role is not set.")
public class RoleIsNotSet extends RuntimeException {

}
