import javax.swing.*;
import java.awt.*;

public class Header extends PPanel {
    private PLabel lblLogo = new PLabel(new ImageIcon(getClass().getResource("icons/Title.png")));
    private PTextField txtfldSearch = new PTextField(10);
    private PButton btnSearch = new PButton(new ImageIcon(getClass().getResource("icons/Icon_Search.png")));
    private PGridBagConstraints gbcCon = new PGridBagConstraints();
    private final Color clrPink = Color.decode("#d7c8c8");
    private final Color clrBlue = Color.decode("#0cc0df");
    private final Color clrGray = Color.decode("#f3e9e9");

    Header(){
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setBorderColor(clrGray);
        setBorderThickness(3);
        setBorderTop(false);
        setBorderRight(false);
        setBorderLeft(false);
        setPadding(new Insets(5,15,5,15));
        add(Box.createHorizontalStrut(30));
        addLogo();
        addSearch();
        addButtons();
    }

    public void addLogo(){
        gbcCon.setConstraints(-1,-1,0,0, GridBagConstraints.VERTICAL);
        lblLogo.setOpaque(true);
        add(lblLogo,gbcCon);
        gbcCon.setConstraints(-1,-1,0.4,0,GridBagConstraints.HORIZONTAL);
        add(Box.createHorizontalStrut(150), gbcCon);
    }

    public void addSearch(){
        txtfldSearch.setRadius(30);
        txtfldSearch.setPlaceholder("Search");
        txtfldSearch.setPadding(new Insets(5,15,5,5));
        txtfldSearch.setBorderColor(Color.decode("#F3E9E9"));
        gbcCon.setConstraints(-1,-1,0.5,0,GridBagConstraints.HORIZONTAL);
        add(txtfldSearch, gbcCon);
        gbcCon.reset();

        btnSearch.setTransparent();
        btnSearch.resizeIcon(24,24);
        add(btnSearch,gbcCon);
    }

    public void addButtons(){
        btnSearch.setHoverClick();
    }

    public static void main(String[] args) {
        JFrame frm = new JFrame("Header");
        frm.setSize(1000, 100);
        frm.add(new Header());
        frm.setDefaultCloseOperation(3);
        frm.setVisible(true);
    }

    public PTextField getSearchTextField(){
        return txtfldSearch;
    }

    public PButton getSearchButton(){
        return btnSearch;
    }
}
