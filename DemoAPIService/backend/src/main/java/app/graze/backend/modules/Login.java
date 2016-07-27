package app.graze.backend.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.graze.backend.lib.JSONException;
import app.graze.backend.lib.JSONObject;
import app.graze.backend.model.User;

/**
 * Created by graze on 26/07/2016.
 */
public class Login extends HttpServlet implements Base {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Please change to use POST method, thanks !!!");
    }

    //Phương thức parse chuỗi ra định dạng JSON
    public JSONObject getJSON(String mess, int codeStatus, Object object) {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("status", codeStatus);
            jsonObj.put("message",mess);
            jsonObj.put("data", object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

    //Khởi tạo một mảng HashMap và khỏi tạo dữ liệu user
    Map<String, String> users;
    Map<String, User> userList;

    void initUsers() {
        users = new HashMap<>();
        users.put("quan", "khaiquan");
        users.put("kaijun", "123456");
        users.put("duynguyen", "123456");
        users.put("locnguyen", "123456");

        userList = new HashMap<>();
        userList.put("quan", new User("t3h@t3h.vn", "Trung tam tin hoc", TOKEN, 1));
        userList.put("kaijun",new User("admin@t3h.vn", "admin", TOKEN, 2));
        userList.put("locnguyen", new User("locnguyen@t3h.vn", "Loc Nguyen", TOKEN, 3));
        userList.put("duynguyen", new User("duynguyen@t3h.vn", "Duy Nguyen", TOKEN, 4));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Tiến hành lấy dữ liệu từ server trả về
        //Dùng lớp String buffer để ghi dữ liệu
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        //Lí do dùng BufferReader là vì server trả về định dạng Reader
        BufferedReader reader = req.getReader(); //You see
        while ((line = reader.readLine()) != null) {
            stringBuffer.append(line);
        }

        //Phải có nha, một số bắt buộc có để nhận dạng kiểu dữ liệu gửi cho phía server
        resp.setHeader("Content-type", "application/json");
        //Khi server gửi req ta xử liệu dữ liệu
        //Xong sẽ gửi response về lại
        JSONObject jsonObject = null;
        //Và đây là quá trình xử liệu và kiểm tra dữ liệu
        try {
            jsonObject = new JSONObject(stringBuffer.toString());
            String userName = jsonObject.getString("userName"); //user_name là tên yêu cầu nhập vào bên phía server
            String pass = jsonObject.getString("pass");

            initUsers();

            String val = users.get(userName);
            if (val != null && val.equals(pass)) {
                resp.getWriter().print(getJSON(null,STATUS_SUCCESS, userList.get(userName).toJson()));
            } else {
                resp.getWriter().println(getJSON("Tai khoan khong dung", 0, null));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            resp.getWriter().println(getJSON("Loi he thong " + jsonObject.toString(), 0, null));
        }

    }
}
