import java.awt.*;

public class PGridBagConstraints extends GridBagConstraints {
    PGridBagConstraints(){
        super();
    }

    public void setInsets(Insets inset){
        this.insets = inset;
    }


    public void setConstraints(int x, int y){
        this.gridx = x;
        this.gridy = y;
    }

    public void setConstraints(int x, int y, double weightx){
        this.gridx = x;
        this.gridy = y;
        this.weightx = weightx;
    }

    public void setConstraints(int x, int y, double weightx, double weighty){
        this.gridx = x;
        this.gridy = y;
        this.weightx = weightx;
        this.weighty = weighty;
    }
    public void setConstraints(int x, int y, double weightx, double weighty, int fill){
        this.gridx = x;
        this.gridy = y;
        this.weightx = weightx;
        this.weighty = weighty;
        this.fill = fill;
    }

    public void setHeight(int height){
        this.gridheight = height;
    }

    public void setWidth(int width){
        this.gridwidth = width;
    }

    public void setWidthHeight(int width, int height){
        this.gridwidth = width;
        this.gridheight = height;
    }


    public void setFill(boolean isFill){
        if(isFill){
            this.fill = GridBagConstraints.BOTH;
        }else{
            this.fill = GridBagConstraints.NONE;
        }
    }

    public void setFill(int fill){
        this.fill = fill;
    }

    public void reset(){
        this.gridx = -1;
        this.gridy = -1;
        this.weightx = 0;
        this.weighty = 0;
        this.fill = 0;
        this.gridwidth = 1;
        this.gridheight = 1;
    }

}