import com.student.util.DBUtil;
import com.student.view.LoginView;

/** 
 * @Description: Main
 * @ClassName: Main
 *  
 */
public class Main {

    public static void main(String[] args) {
        new LoginView();
        DBUtil.getDBUtil().close();
    }
}
