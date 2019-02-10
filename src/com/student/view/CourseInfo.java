package com.student.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import com.student.AppConstants;
import com.student.base.BaseDAO.CourseExistException;
import com.student.base.BaseDAO.CourseNotFoundException;
import com.student.base.BaseDAO.CourseSelectedException;
import com.student.dao.AdminDAO;

public class CourseInfo extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel container;
    private JTable courseMess;
    private static final String[] infocolumn = {AppConstants.CNO, AppConstants.CNAME,
            AppConstants.CREDIT, AppConstants.CDEPT, AppConstants.TNAME};
    private JLabel totCount;
    private AdminView frame;

    public CourseInfo(AdminView frame) {
        super(frame, AppConstants.ADMIN_COURSEINFO, true);
        this.frame = frame;
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

        JButton addBtn = new JButton(AppConstants.ADMIN_COURSEINFO_ADD);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton delBtn = new JButton(AppConstants.ADMIN_COURSEINFO_DEL);
        delBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton quitBtn = new JButton(AppConstants.ADMIN_COURSEINFO_QUIT);
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
                frame.genChoice();
            }
        });
    }

    public void initTable() {
        JPanel panel = new JPanel();
        container.add(panel, BorderLayout.CENTER);
        courseMess = new JTable();
        courseMess.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        totCount = new JLabel();
        panel.add(totCount, BorderLayout.NORTH);
        courseMess.setEnabled(false);
        courseMess.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(courseMess);
        scrollPane.setPreferredSize(new Dimension(300, 180));
        panel.add(scrollPane);
        genTable();
    }

    public void genTable() {
        String[][] result = AdminDAO.getInstance().getAllCourses();
        courseMess.setModel(new DefaultTableModel(result, infocolumn) {
            private static final long serialVersionUID = 1L;
        });
        totCount.setText(AppConstants.TOTAL_COUNT + String.valueOf(courseMess.getRowCount()));
    }

    private class AddCourse extends JDialog {

        private static final long serialVersionUID = 1L;

        private JPanel contPanel;
        private JTextField[] tFields;
        private final String[] checkregex = {AppConstants.REGEX_CNO, AppConstants.REGEX_CNAME,
                AppConstants.REGEX_CREDIT, AppConstants.REGEX_CDEPT, AppConstants.REGEX_TNAME};
        private final boolean[] checknull = {false, false, true, true, true};

        public AddCourse(CourseInfo frame) {
            super(frame, AppConstants.ADMIN_COURSEINFO_ADD, true);
            contPanel = new JPanel();
            setContentPane(contPanel);
            setLayout(new BorderLayout(5, 5));
            setResizable(false);
            setLocationRelativeTo(null);
            setSize(310, 260);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            initBtn();
            initTable();
        }

        public void initBtn() {
            JPanel panel = new JPanel();
            JButton jb = new JButton(AppConstants.VERIFY);
            panel.add(jb);
            contPanel.add(panel, BorderLayout.SOUTH);
            getRootPane().setDefaultButton(jb);
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] info = new String[5];
                    for (int i = 0; i < 5; i++) {
                        info[i] = tFields[i].getText();
                    }
                    boolean isVaild = true;
                    for (int i = 0; i < 5; i++) {
                        if (Pattern.matches(checkregex[i], info[i]) == false) {
                            isVaild = false;
                            tFields[i].setBackground(Color.PINK);
                        } else {
                            tFields[i].setBackground(Color.WHITE);
                        }
                        if (checknull[i] && info[i].equals("")) {
                            info[i] = null;
                        }
                    }
                    if (!isVaild) {
                        return;
                    }
                    try {
                        AdminDAO.getInstance().AddCourse(info);
                    } catch (CourseExistException e1) {
                        JOptionPane.showMessageDialog(null, AppConstants.ADMIN_CNO_EXIST_ERROR,
                                AppConstants.ERROR, JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    dispose();
                    CourseInfo.this.genTable();
                }
            });
        }

        public void initTable() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
            JPanel[] panels = new JPanel[5];
            JLabel[] labels = new JLabel[5];
            tFields = new JTextField[5];
            for (int i = 0; i < 5; i++) {
                panels[i] = new JPanel();
                panels[i].setLayout(new GridLayout(1, 2, 5, 5));
                labels[i] = new JLabel(infocolumn[i]);
                tFields[i] = new JTextField(10);
                panels[i].add(labels[i], JLabel.CENTER_ALIGNMENT);
                panels[i].add(tFields[i], JLabel.CENTER_ALIGNMENT);
                panel.add(panels[i]);
            }
            contPanel.add(panel, BorderLayout.CENTER);
        }
    }

    private class DelCourse extends JDialog {

        private static final long serialVersionUID = 1L;
        private JPanel contPanel;
        private JTextField tField;

        public DelCourse(CourseInfo frame) {
            super(frame, AppConstants.ADMIN_COURSEINFO_DEL, true);
            contPanel = new JPanel();
            setContentPane(contPanel);
            setLayout(new BorderLayout(5, 5));
            setResizable(false);
            setLocationRelativeTo(null);
            setSize(280, 120);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            initBtn();
            initTable();
        }

        public void initBtn() {
            JPanel panel = new JPanel();
            JButton jb = new JButton(AppConstants.DELETE);
            panel.add(jb);
            contPanel.add(panel, BorderLayout.SOUTH);
            getRootPane().setDefaultButton(jb);
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cno = tField.getText();
                    if (Pattern.matches(AppConstants.REGEX_SNO, cno) == false) {
                        tField.setBackground(Color.PINK);
                        return;
                    } else {
                        tField.setBackground(Color.WHITE);
                    }
                    try {
                        AdminDAO.getInstance().DelCourse(cno);
                    } catch (CourseNotFoundException e1) {
                        JOptionPane.showMessageDialog(null, AppConstants.ADMIN_CNO_NOTEXIST_ERROR,
                                AppConstants.ERROR, JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (CourseSelectedException e2) {
                        JOptionPane.showMessageDialog(null, AppConstants.ADMIN_COURSESELECTED_ERROR,
                                AppConstants.ERROR, JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    dispose();
                    CourseInfo.this.genTable();
                }
            });
        }

        public void initTable() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
            JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayout(1, 2, 5, 5));
            JLabel label = new JLabel(AppConstants.CNO);
            tField = new JTextField(10);
            panel2.add(label, JLabel.CENTER_ALIGNMENT);
            panel2.add(tField, JLabel.LEFT_ALIGNMENT);
            panel.add(panel2);
            contPanel.add(panel, BorderLayout.CENTER);
        }
    }
}
