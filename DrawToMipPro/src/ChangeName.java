import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.event.EditorMouseEvent;
import com.intellij.openapi.editor.event.EditorMouseListener;
import com.intellij.openapi.editor.event.EditorMouseMotionListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

/**
 * Created by totem on 8/22/15.
 */
public class ChangeName extends AnAction {

    private boolean isChange = false;

    public void actionPerformed(AnActionEvent anActionEvent) {
        // TODO: insert action logic here
        Application application = ApplicationManager.getApplication();
        final MyAppComponet myComponent = application.getComponent(MyAppComponet.class);

        //Get all the required data from data keys
        final Editor editor = anActionEvent.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = anActionEvent.getRequiredData(CommonDataKeys.PROJECT);
        //Access document, caret, and selection
        final Document document = editor.getDocument();
        final SelectionModel selectionModel = editor.getSelectionModel();

        isChange = !isChange;

        editor.addEditorMouseListener(new EditorMouseListener() {
            @Override
            public void mousePressed(EditorMouseEvent editorMouseEvent) {

            }

            @Override
            public void mouseClicked(EditorMouseEvent editorMouseEvent) {

                if (!isChange) return;
                //Editor editor = editorMouseEvent.getData(CommonDataKeys.EDITOR);

                final int start = selectionModel.getSelectionStart();
                final int end = selectionModel.getSelectionEnd();
                //New instance of Runnable to make a replacement
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        document.replaceString(start, start + ("mipmap").length(), "mipmap");
                    }
                };
                //Making the replacement
                WriteCommandAction.runWriteCommandAction(project, runnable);
                selectionModel.removeSelection();
            }

            @Override
            public void mouseReleased(EditorMouseEvent editorMouseEvent) {

            }

            @Override
            public void mouseEntered(EditorMouseEvent editorMouseEvent) {

            }

            @Override
            public void mouseExited(EditorMouseEvent editorMouseEvent) {

            }
        });

    }

    @Override
    public void update(AnActionEvent event) {
        Editor editor = event.getData(CommonDataKeys.EDITOR);
        //event.getPresentation().setVisible(true);
        //event.getPresentation().setEnabled(editor != null);
        //event.getPresentation().setIcon(AllIcons.General.Error);
        if(isChange){
            event.getPresentation().setText("Change Name (Stop)");
        }else {
            event.getPresentation().setText("Change Name (Start)");
        }

        //event.getPresentation().
    }
}
