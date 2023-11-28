package fr.eni.papeterie.bll;

@SuppressWarnings("serial")
public class BLLException extends Exception {


    public BLLException() {
        super();
    }

    public BLLException(String _message) {
        super(_message);
    }
    
    public BLLException(String _message, Throwable _exception) {
        super(_message, _exception);
    }

    @Override
    public String getMessage() {
        return "BLL - " + super.getMessage();
    }

}
