package fi.muni.pa165.hauntedhouses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Klara Kufova, 410091
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The requested resource was not found.")
public class ResourceNotFound extends RuntimeException {

}
