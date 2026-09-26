package exceptions;

public class InvalidAppointmentException extends HospitalException {
    public InvalidAppointmentException(String message) {
        super(message);
    }
}
