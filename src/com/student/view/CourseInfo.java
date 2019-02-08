package com.student.view;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Container;
import com.student.AppConstants;
import com.student.dao.AdminDAO;
import com.student.model.Course;
import javax.swing.JDialog;

public class CourseInfo extends JFrame {
    private JPanel container;
    private JTable stuMess;
    private static String[] infocolumn = { AppConstants.CNO, AppConstants.CNAME, AppConstants.CREDIT,
            AppConstants.CDEPT, AppConstants.TNAME };

    public CourseInfo() {
        // super(frame, AppConstants.ADMIN_COURSEINFO, true);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(450, 300);
        setTitle(AppConstants.ADMIN_COURSEINFO);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        container = new JPanel();
        setContentPane(container);
        container.setLayout(new BorderLayout(5, 5));

        initBtn();
        initTable();

    }

    public void initBtn() {
        JPanel panel = new JPanel();
        container.add(panel, BorderLayout.EAST);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton addBtn = new JButton(AppConstants.ADMIN_couresInfo_ADD);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton delBtn = new JButton(AppConstants.ADMIN_couresInfo_delete);
        delBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton quitBtn = new JButton(AppConstants.ADMIN_couresInfo_quit);
        quitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(100, 30)));
        panel.add(addBtn);
        panel.add(Box.createRigidArea(new Dimension(100, 30)));
        panel.add(delBtn);
        panel.add(Box.createRigidArea(new Dimension(100, 30)));
        panel.add(quitBtn);

        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourse ac = new AddCourse(CourseInfo.this);
                ac.setVisible(true);
            }
        });
        delBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DelCourse dc = new DelCourse(CourseInfo.this);
                dc.setVisible(true);
            }
        });
        quitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void initTable() {
        JPanel panel = new JPanel();
        container.add(panel, BorderLayout.CENTER);
        String[][] result = AdminDAO.getInstance().getAllCourse();
        stuMess = new JTable(result, infocolumn);
        stuMess.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JLabel lable = new JLabel("记录总数:" + String.valueOf(stuMess.getRowCount()));
        panel.add(lable, BorderLayout.NORTH);

        stuMess.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(stuMess);
        scrollPane.setPreferredSize(new Dimension(300, 180));
        panel.add(scrollPane);
    }

    public void genTable(JTable jTable, String[][] result, String[] column) {
        jTable.setModel(new DefaultTableModel(result, column) {
            private static final long serialVersionUID = 1L;
        });
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    private class AddCourse extends JDialog {
        private JPanel contPanel;
        private JPanel[] panels;
        private JTextField[] tFields;

        public AddCourse(CourseInfo frame) {
            super(frame, "添加课程", true);
            contPanel = new JPanel();
            setContentPane(contPanel);
            setLayout(new BorderLayout(5, 5));
            setResizable(false);
            setLocationRelativeTo(null);
            setSize(380, 280);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            initBtn();
            initTable();
        }

        public void initBtn() {
            JPanel panel = new JPanel();
            JButton jb = new JButton("确认");
            // jb.setAlignmentX(CENTER_ALIGNMENT);
            // jb.setAlignmentY(CENTER_ALIGNMENT);
            // panel.add(Box.createRigidArea(new Dimension(100, 30)));
            panel.add(jb);
            contPanel.add(panel, BorderLayout.EAST);

            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] info = new String[5];
                    for (int i = 0; i < 5; i++)
                        info[i] = tFields[i].getText();
                    AdminDAO.getInstance().AddCourse(info);
                    dispose();
                    CourseInfo.this.initTable();
                }
            });
        }

        public void initTable() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(5));
            panels = new JPanel[5];
            JLabel[] labels = new JLabel[5];
            tFields = new JTextField[5];
            for (int i = 0; i < 5; i++) {
                panels[i] = new JPanel();
                // panels[i].setPreferredSize(new Dimension(50, 50));
                panels[i].setLayout(new FlowLayout(3));
                labels[i] = new JLabel(infocolumn[i]);
                tFields[i] = new JTextField(10);
                labels[i].setAlignmentX(JLabel.LEFT_ALIGNMENT);
                tFields[i].setAlignmentX(JTextField.RIGHT_ALIGNMENT);
                panels[i].add(labels[i]);
                panels[i].add(tFields[i]);
                panels[i].setAlignmentY(CENTER_ALIGNMENT);
                panel.add(panels[i]);
            }
            contPanel.add(panel, BorderLayout.CENTER);
        }
    }

    private class DelCourse extends JDialog {
        private JPanel contPanel;
        private JTextField tField;

        public DelCourse(CourseInfo frame) {
            super(frame, "删除课程", true);
            contPanel = new JPanel();
            setContentPane(contPanel);
            setLayout(new BorderLayout(5, 5));
            setResizable(false);
            setLocationRelativeTo(null);
            setSize(380, 280);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            initBtn();
            initTable();
        }

        public void initBtn() {
            JPanel panel = new JPanel();
            JButton jb = new JButton("删除");
            panel.add(jb);
            contPanel.add(panel, BorderLayout.EAST);

            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AdminDAO.getInstance().DelCourse(tField.getText());
                    dispose();
                    CourseInfo.this.initTable();
                }
            });
        }

        public void initTable() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(5));
            JLabel label = new JLabel("CNO");
            tField = new JTextField(10);
            label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
            tField.setAlignmentX(JTextField.RIGHT_ALIGNMENT);
            panel.add(label);
            panel.add(tField);
            contPanel.add(panel, BorderLayout.CENTER);
        }
    }

    public static void main(String[] args) {
        new CourseInfo().setVisible(true);
    }
}
