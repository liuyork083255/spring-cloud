package liu.york.spring.cloud.zuul.model;

public class ResponseUtil {

    public static ResponseModel error(int code, String msg){
        ResponseModel model = new ResponseModel();
        model.setCode(code);
        model.setSuccess(false);
        model.setMessage(msg);
        return model;
    }
}