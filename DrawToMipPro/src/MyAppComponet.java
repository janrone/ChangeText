import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;
import com.intellij.util.messages.impl.Message;
import org.jetbrains.annotations.NotNull;


/**
 * Created by totem on 8/22/15.
 */
public class MyAppComponet implements ApplicationComponent {
    public MyAppComponet() {
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "MyAppComponet";
    }

    public void sayHello(String string) {

        // Show dialog with message

        Messages.showMessageDialog(

                "Hello World!",

                string,

                Messages.getInformationIcon()

        );

    }

}
