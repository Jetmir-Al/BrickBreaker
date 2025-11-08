import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreboardWindow extends JFrame {

    public ScoreboardWindow() {
        setTitle("Scoreboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        Map<String, User> users = UserManager.loadUsers();

        List<User> sortedUsers = users.values().stream()
                .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                .collect(Collectors.toList());

        String[] colNames = {"User", "Highest Score"};
        DefaultTableModel tableModel = new DefaultTableModel(colNames, 0);

        for (User u : sortedUsers) {
            tableModel.addRow(new Object[]{u.getUsername(), u.getScore()});
        }

        JTable table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        add(new JScrollPane(table));
        setVisible(true);
    }
}
