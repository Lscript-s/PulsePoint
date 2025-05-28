import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PieChartDemo extends JFrame {

    public PieChartDemo() {
        setTitle("Donut Chart Example");

        // Dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Java", 40);
        dataset.setValue("Python", 30);
        dataset.setValue("C++", 20);
        dataset.setValue("JavaScript", 10);

        // Chart using RingPlot
        JFreeChart chart = ChartFactory.createRingChart(
                "",
                dataset,
                true,
                true,
                false
        );

        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setLabelGenerator(null);
        plot.setSectionDepth(0.35);
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setOutlinePaint(null);
        // Render chart to BufferedImage
        int width = 400;
        int height = 300;
        BufferedImage chartImage = chart.createBufferedImage(width, height);

        // Create ImageIcon from BufferedImage
        ImageIcon chartIcon = new ImageIcon(chartImage);

        // Create JLabel with icon
        JLabel chartLabel = new JLabel(chartIcon);
        // Add label to JFrame content pane
        add(chartLabel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PieChartDemo::new);
    }
}
