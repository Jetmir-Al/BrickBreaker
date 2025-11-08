import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreBoard extends JFrame {

    private JTable ScoreBoard;
    private JPanel panel1;

    public ScoreBoard(){
        setTitle("Scoreboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        String[] colNames = {"User", "Highest Score"};
        ScoreBoard = new JTable(new DefaultTableModel(colNames, 0));

        ScoreBoard.setRowHeight(25);
        ScoreBoard.setFont(new Font("Arial", Font.PLAIN, 16));
        ScoreBoard.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        Map<String, User> users = UserManager.loadUsers();

        DefaultTableModel tableModel = (DefaultTableModel) ScoreBoard.getModel();
//        DefaultTableModel manages data
//        JTable only displays

        for (User user : new ArrayList<>(users.values())){
            tableModel.addRow( new Object[]{

                    user.getUsername(),
                    user.getScore()
            });
        }

        add(new JScrollPane(ScoreBoard));
        setVisible(true);
    }
}
