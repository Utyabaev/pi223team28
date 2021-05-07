package rgr;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.*;


public class Calc extends JFrame {
    private MyDocumentFilter documentFilter;
    JTextField tiraj_field, tiraj_field2, result;
    int mn_size_leaflets = 0,mn_size_bcards = 0, mn_format = 0, mn_lamination = 0, mn_color = 0, mn_product = 1; double discount = 0;
    JCheckBox card_y, card_n, leaflets, b_cards;
    boolean test;
    
    public static double calc_bcards(int tiraj, int paper, int phormat, int lam, int ang, int type, int dis){
    	double price = 0;
    	if (paper == 1) {
    		price +=1;
    	}
    	if (paper == 2) {
    		price +=5;
    	}
    	if (paper == 3) {
    		price +=2;
    	}
    	if (paper == 4) {
    		price +=6;
    	}
    	if (phormat == 1) {
    		price +=10;
    	}
    	if (phormat == 2) {
    		price +=9;
    	}
    	if (lam == 1) {
    		price +=40;
    	}
    	if (ang == 1) {
    		price +=30;
    	}
    	if (type == 1) {
    		price +=9;
    	}
    	if (type == 2) {
    		price +=4;
    	}
    	if (type == 3) {
    		price +=3;
    	}
    	if (type == 4) {
    		price +=1;
    	}
    	if (dis == 1) {
    		price -= price * 0.15;
    	}
    	return price * tiraj;
    }
    
    public Calc() {

        final Error_msg error_msg_paper = new Error_msg();
        final Error_msg error_msg_tiraj = new Error_msg();
        final Error_msg error_msg_phormat = new Error_msg();
        final Error_msg error_msg_perforation = new Error_msg();
        final  Error_msg error_msg_type = new Error_msg();

        JFrame frame = new JFrame("Калькулятор стоимости производства рекламных материалов. Полиграфия.");
        frame.setLayout(null);
        frame.setSize(490, 580);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        JPanel contents = new JPanel(new FlowLayout());
        contents.setLayout(null);
        contents.setBounds(10, 10, 710, 710);

        JLabel tiraj_text2 = new JLabel("Тираж");
        tiraj_text2.setBounds(150, 45, 200, 15);
        contents.add(tiraj_text2);

        tiraj_field = new JTextField(15);
        ((AbstractDocument) tiraj_field.getDocument()).setDocumentFilter(
                new MyDocumentFilter());
        tiraj_field.setBounds(150, 60, 200, 25);
        contents.add(tiraj_field);
        tiraj_field.setEnabled(false);

        final JCheckBox leaflets = new JCheckBox("Листовки");
        leaflets.setBounds (160,10,100,25);
        contents.add(leaflets);

        final JCheckBox bcards = new JCheckBox("Визитки");
        bcards.setBounds(260,10,100,25);
        contents.add(bcards);
        
        /*bcards.setVisible(false);
        leaflets.setVisible(false);

        String[] ch_ad = {"--ВЫБЕРИТЕ--","Визитки","Листовки"};
        final JComboBox ch_ad2 = new JComboBox(ch_ad);
        ch_ad2.setBounds(150, 10, 200, 25);
        contents.add(ch_ad2);*/
       
        String[] ch_paper = {"--ВЫБЕРИТЕ--","105гр мелованная","170гр мелованная","300гр мелованная","80гр офсетная"};
        final JComboBox paper = new JComboBox(ch_paper);
        paper.setBounds(20, 110, 200, 25);
        contents.add(paper);
        paper.setEnabled(false);

        String[] ch_paper2 = {"--ВЫБЕРИТЕ--","200гр глянцевая","300гр глянцевая","200гр матовая","300гр матовая"};
        final JComboBox paper2 = new JComboBox(ch_paper2);
        paper2.setBounds(250, 110, 200, 25);
        contents.add(paper2);
        paper2.setEnabled(false);

        String[] ch_phormat_leaflets = {"--ВЫБЕРИТЕ--","A4","A5","A6","A7"};
        final JComboBox ch_phormat_leaflets2 = new JComboBox(ch_phormat_leaflets);
        ch_phormat_leaflets2.setBounds  (20, 160, 200, 25);
        contents.add(ch_phormat_leaflets2);
        ch_phormat_leaflets2.setEnabled(false);

        String[] ch_phormat_bcards = {"--ВЫБЕРИТЕ--","Стандарт (50х90мм)","Евро (55х85мм)"};
        final JComboBox ch_phormat_bcards2 = new JComboBox(ch_phormat_bcards);
        ch_phormat_bcards2.setBounds(250, 160, 200, 25);
        contents.add(ch_phormat_bcards2);
        ch_phormat_bcards2.setEnabled(false);

        JLabel perforation_text = new JLabel("Перфорация");
        perforation_text.setBounds(20, 195, 200, 15);
        contents.add(perforation_text);

        String[] ch_perforation = {"--ВЫБЕРИТЕ--","1 сгиб/линия","2 сгиба/линии","3 сгиба/линии","4 сгиба/линии"};
        final JComboBox ch_perforation2 = new JComboBox(ch_perforation);
        ch_perforation2.setBounds(20, 210, 200, 25);
        contents.add(ch_perforation2);
        ch_perforation2.setEnabled(false);

        JLabel color_text = new JLabel("Доп. обработка");
        color_text.setBounds(250, 195, 200, 15);
        contents.add(color_text);

        final JCheckBox lamination = new JCheckBox("Ламинация");
        lamination .setBounds(250,210,90,25);
        contents.add(lamination);
        lamination.setEnabled(false);

        final JCheckBox angles = new JCheckBox("Скругление углов");
        angles .setBounds(340,210,200,25);
        contents.add(angles);
        angles.setEnabled(false);

        JLabel print_type = new JLabel("Тип печати");
        print_type.setBounds(20, 245, 200, 15);
        contents.add(print_type);

        String[] ch_type_leaflets = {"--ВЫБЕРИТЕ--","4+4 (двусторонняя)","4+0 (односторонняя)","1+0 (ч/б односторонняя)","1+1 (ч/б двусторонняя)"};
        final JComboBox ch_type_leaflets2 = new JComboBox(ch_type_leaflets);
        ch_type_leaflets2.setBounds(20, 260, 200, 25);
        contents.add(ch_type_leaflets2);
        ch_type_leaflets2.setEnabled(false);

        JLabel print_type2 = new JLabel("Тип печати");
        print_type2.setBounds(250, 245, 200, 15);
        contents.add(print_type2);

        String[] ch_type_bcards = {"--ВЫБЕРИТЕ--","4+4 (двусторонняя)","4+0 (односторонняя)","1+0 (ч/б односторонняя)","1+1 (ч/б двусторонняя)"};
        final JComboBox ch_type_bcards2 = new JComboBox(ch_type_bcards);
        ch_type_bcards2.setBounds(250, 260, 200, 25);
        contents.add(ch_type_bcards2);
        ch_type_bcards2.setEnabled(false);

        JLabel card = new JLabel("Дисконтная карта (15% скидка)");
        card.setBounds(135, 310, 232, 15);
        contents.add(card);

        card_y = new JCheckBox("Есть");
        card_y.setBounds(135, 325, 75, 25);
        contents.add(card_y);
        card_y.setEnabled(false);

        card_n = new JCheckBox("Нет", true);
        card_n.setBounds(230, 325, 75, 25);
        contents.add(card_n);
        card_n.setEnabled(false);

        JLabel size_leaflets = new JLabel("Формат");
        size_leaflets.setBounds(250, 145, 200, 15);
        contents.add(size_leaflets);

        JLabel size_bcards = new JLabel("Формат");
        size_bcards.setBounds(20, 145, 200, 15);
        contents.add(size_bcards);

        JLabel material_leaflets = new JLabel("Бумага");
        material_leaflets.setBounds(250, 95, 200, 15);
        contents.add(material_leaflets);

        JLabel material_bcards = new JLabel("Бумага");
        material_bcards.setBounds(20, 95, 200, 15);
        contents.add(material_bcards);

        leaflets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leaflets.isSelected()) {
                    bcards.setSelected(false);
                }
            }
        });
        
        if (bcards.isSelected() == false) {
        	test = false;
        }
        
        
        
        leaflets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(leaflets.isSelected())) {
                    leaflets.setSelected(true);
                }
            }
        });
        
        bcards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bcards.isSelected()) {
                    leaflets.setSelected(false);
                    mn_size_bcards = 0;
                }
            }
        });

        bcards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(bcards.isSelected())) {
                    bcards.setSelected(true);
                }
            }
        });

        card_y.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( !(card_n.isSelected()) ) {
                    card_y.setSelected(true);
                }
            }
        });

        card_y.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (card_n.isSelected()) {
                    card_n.setSelected(false);
                }
            }
        });

        card_n.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( !(card_y.isSelected()) ) {
                    card_n.setSelected(true);
                }
            }
        });

        card_n.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (card_y.isSelected()) {
                    card_y.setSelected(false);
                }
            }
        });

        JLabel result_text = new JLabel("Стоимость производства рекламных материалов:");
        result_text.setBounds(90,355,352,30);
        contents.add(result_text);

        final JTextField result = new JTextField();
        result.setBounds(110, 380, 250, 30);
        result.setText(" 0");
        result.setEditable(false);
        contents.add(result);

        final JButton calculate = new JButton("Рассчитать");
        calculate.setBounds(160, 420, 150, 30);
        contents.add(calculate);
        calculate.setEnabled(false);

        final JButton close = new JButton("ВЫХОД");
        close.setBounds(160, 460, 150, 30);
        contents.add(close);

        final JButton info = new JButton("Информация");
        info.setBounds(160, 500, 150, 30);
        ActionListener informationListener = new Info();
        info.addActionListener(informationListener);
        contents.add(info);
        
        leaflets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card_y.setEnabled(true);
                card_n.setEnabled(true);
            tiraj_field.setEnabled(true);
            paper.setEnabled(true);
            ch_phormat_leaflets2.setEnabled(true);
            ch_perforation2.setEnabled(true);
            ch_type_leaflets2.setEnabled(true);
            calculate.setEnabled(true);
                paper2.setEnabled(false);
                ch_phormat_bcards2.setEnabled(false);
                lamination.setEnabled(false);
                angles.setEnabled(false);
                ch_type_bcards2.setEnabled(false);
            }
        });

        bcards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiraj_field.setEnabled(true);
                card_y.setEnabled(true);
                card_n.setEnabled(true);
                paper2.setEnabled(true);
                ch_phormat_bcards2.setEnabled(true);
                lamination.setEnabled(true);
                angles.setEnabled(true);
                ch_type_bcards2.setEnabled(true);
                calculate.setEnabled(true);
                paper.setEnabled(false);
                ch_phormat_leaflets2.setEnabled(false);
                ch_perforation2.setEnabled(false);
                ch_type_leaflets2.setEnabled(false);
            }
        });
        
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int paper_leaflets_val = 0, paper_bcards_val = 0, phormat_leaflets_val = 0, phormat_bcards_val = 0,
                        perforation_val = 0, lam_val = 0, ang_val = 0, type_leaflets_val = 0, type_bcards_val = 0, tiraj_val = 0;

                if (card_y.isSelected()) {
                    discount = 0.15;
                }

               Paper leaflets_pap = new Paper();
               Paper bcards_pap = new Paper();
                
                String msg = (String)paper.getSelectedItem();
                switch (msg) {
                    case "--ВЫБЕРИТЕ--":
                    leaflets_pap.setPrice(0);
                        break;
                    case "105гр мелованная":
                    leaflets_pap.setPrice(2);
                        break;
                    case "170гр мелованная": 
                    leaflets_pap.setPrice(3);
                        break;
                    case "300гр мелованная":
                    leaflets_pap.setPrice(4);
                        break;
                    case "80гр офсетная": 
                    leaflets_pap.setPrice(5);
                        break;

                }

                String msg2 = (String)paper2.getSelectedItem();
                switch (msg2) {
                    case "--ВЫБЕРИТЕ--": 
                    bcards_pap.setPrice(0);
                        break;
                    case "200гр глянцевая": 
                    bcards_pap.setPrice(1);                   
                        break;
                    case "300гр глянцевая": 
                    bcards_pap.setPrice(5);
                        break;
                    case "200гр матовая": 
                    bcards_pap.setPrice(2);
                        break;
                    case "300гр матовая": 
                    bcards_pap.setPrice(6);
                        break;

                }
                
                Phormat ph_leaflets = new Phormat();
                Phormat ph_bcards = new Phormat();

                String msg3 = (String)ch_phormat_leaflets2.getSelectedItem();
                switch (msg3) {
                    case "--ВЫБЕРИТЕ--":
                    ph_leaflets.setPrice(0);
                        break;
                    case "A4": 
                    ph_leaflets.setPrice(10);
                        break;
                    case "A5":
                    ph_leaflets.setPrice(8);
                        break;
                    case "A6": 
                    ph_leaflets.setPrice(6);
                        break;
                    case "A7":
                    ph_leaflets.setPrice(4);
                        break;

                }

                String msg4 = (String)ch_phormat_bcards2.getSelectedItem();
                switch (msg4) {
                    case "--ВЫБЕРИТЕ--":
                    ph_bcards.setPrice(0);
                        break;
                    case "Стандарт (50х90мм)": 
                    ph_bcards.setPrice(10);
                        break;
                    case "Евро (55х85мм)":
                    ph_bcards.setPrice(9);
                        break;

                }
                
                Perforation per = new Perforation();

                String msg5 = (String)ch_perforation2.getSelectedItem();
                switch (msg5) {
                    case "--ВЫБЕРИТЕ--": 
                    per.setPrice(0);
                        break;
                    case "1 сгиб/линия": 
                    per.setPrice(2);
                        break;
                    case "2 сгиба/линии": 
                    per.setPrice(4);
                        break;
                    case "3 сгиба/линии": 
                    per.setPrice(6);
                        break;
                    case "4 сгиба/линии":
                    per.setPrice(8);
                        break;

                }
                
                Typee t_leaflets = new Typee();
                Typee t_bcards = new Typee();

                String msg6 = (String)ch_type_leaflets2.getSelectedItem();
                switch (msg6) {
                    case "--ВЫБЕРИТЕ--": 
                    t_leaflets.setPrice(0);
                        break;
                    case "4+4 (двусторонняя)":
                    t_leaflets.setPrice(10);
                        break;
                    case "4+0 (односторонняя)": 
                    t_leaflets.setPrice(5);
                        break;
                    case "1+0 (ч/б односторонняя)": 
                    t_leaflets.setPrice(2);
                        break;
                    case "1+1 (ч/б двусторонняя)": 
                    t_leaflets.setPrice(1);
                        break;

                }

                String msg7 = (String)ch_type_bcards2.getSelectedItem();
                switch (msg7) {
                    case "--ВЫБЕРИТЕ--": 
                    t_bcards.setPrice(0);
                        break;
                    case "4+4 (двусторонняя)": 
                    t_bcards.setPrice(9);
                        break;
                    case "4+0 (односторонняя)":
                    t_bcards.setPrice(4);
                        break;
                    case "1+0 (ч/б односторонняя)":
                    t_bcards.setPrice(3);
                        break;
                    case "1+1 (ч/б двусторонняя)":
                    t_bcards.setPrice(1);
                        break;

                }
                
                Lamination lam = new Lamination();
                Angles ang = new Angles();

                if (lamination.isSelected()) {
                    lam.setPrice(40);
                }

                if (angles.isSelected()) {
                    ang.setPrice(30);
                }

                if(tiraj_field.getText().trim().equals("0") || tiraj_field.getText().trim().isEmpty())
                {
                    error_msg_tiraj.crash_tiraj();
                    return;
                }
                
                paper_leaflets_val = leaflets_pap.getPrice();
                phormat_leaflets_val = ph_leaflets.getPrice();
                perforation_val = per.getPrice();
                type_leaflets_val = t_leaflets.getPrice();
                
                paper_bcards_val = bcards_pap.getPrice(); 
                phormat_bcards_val = ph_bcards.getPrice();
                type_bcards_val= t_bcards.getPrice();
                lam_val = lam.getPrice();
                ang_val = ang.getPrice();
                

                if ( (leaflets.isSelected()) || (bcards.isSelected()) ) {
                    String tiraj_val_str = tiraj_field.getText();
                    tiraj_val = Integer.parseInt(tiraj_val_str);
                }

                if ( (paper_leaflets_val == 0) && leaflets.isSelected())  {
                    error_msg_paper.crash_paper();
                    return;
                }

                if ( (paper_bcards_val == 0) && bcards.isSelected())  {
                    error_msg_paper.crash_paper();
                    return;
                }

                if ( (phormat_leaflets_val == 0) && (leaflets.isSelected()) ) {
                    error_msg_phormat.crash_phormat();
                    return;
                }

                if ( (phormat_bcards_val == 0) && (bcards.isSelected()) ) {
                    error_msg_phormat.crash_phormat();
                    return;
                }

                if ( (perforation_val == 0) && leaflets.isSelected() ) {
                    error_msg_perforation.crash_perforation();
                    return;
                }

                if ( (type_leaflets_val == 0) && leaflets.isSelected() ) {
                    error_msg_type.crash_type();
                    return;
                }

                if ( (type_bcards_val == 0) && bcards.isSelected() ) {
                    error_msg_type.crash_type();
                    return;
                }
                
                if (leaflets.isSelected()) {
                	double rezz1 = (paper_leaflets_val + phormat_leaflets_val + perforation_val + type_leaflets_val) * tiraj_val;
                    double rez1 = rezz1 - rezz1 * discount;
                    String rez1_value = ""+rez1;
                    try {
                        FileWriter writer = new FileWriter("Результат.txt");
                        writer.write("Листовки: " +
                        		"\nТираж: " + tiraj_val + 
                        		"\nСтоимость бумаги: " + paper_leaflets_val + 
                        		"\nСтоимость формата: " + phormat_leaflets_val + 
                        		"\nСтоимость перфорации: " + perforation_val + 
                        		"\nСтоимость типа: " + type_leaflets_val +
                        		"\nСкидка: " + discount + 
                        		"\nЦена: " + rez1_value);
                        writer.close();
                    } catch(IOException ex) {
                        ex.printStackTrace();
                    }
                    result.setText(rez1_value+" Рублей");
                    discount = 0;
                    mn_size_leaflets = 0;
                    return;
                }

                if (bcards.isSelected()) {
                	double rezz2 = (paper_bcards_val + phormat_bcards_val + type_bcards_val + lam_val + ang_val) * tiraj_val;
                    double rez4 = rezz2 - rezz2 * discount;
                    String rez4_value = ""+rez4;
                    try {
                        FileWriter writer = new FileWriter("Результат.txt");
                        writer.write("Визитки" + 
                        		"\nТираж: " + tiraj_val + 
                        		"\nСтоимость бумаги: " + paper_bcards_val + 
                        		"\nСтоимость формата: " + phormat_bcards_val + 
                        		"\nСтоимость типа: " + type_bcards_val + 
                        		"\nСтоимость ламинации: " + lam_val + 
                        		"\nСтоимость скругления углов: " + ang_val +
                        		"\nСкидка: " + discount + 
                        		"\nЦена: " + rez4_value);
                        writer.close();
                    } catch(IOException ex) {
                        ex.printStackTrace();
                    }
                    result.setText(rez4_value+" Рублей");
                    discount = 0;
                    mn_size_bcards = 0;
                    return;
                }
                    }
        });

        setContentPane(contents);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        frame.add(contents);

        frame.setLayout(null);

        contents.setVisible(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public static void main(String[] args) { 
    	Runnable runnable = new Runnable()
    	{
    			@Override
    			public void run()
    			{	
    			new Calc();
    			}
    			};
    			EventQueue.invokeLater(runnable);
    			
    			}
    
    }