/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.backing;


import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.charts.ChartData;


/**
 *
 * @author bartek
 */
@Named(value = "chartManagedBean")
@RequestScoped
public class ChartManagedBean {
    
    private LineChartModel lineModel;

    @PostConstruct
    public void init() {
        lineModel = new LineChartModel();
        ChartData data = new ChartData();
        
        LineChartSeries sineSeries = new LineChartSeries();
        LineChartSeries cosineSeries = new LineChartSeries();
        
        for (int i = 0; i <= 360; i+=10) {
            sineSeries.set(i, Math.sin(Math.toRadians(i)));
            cosineSeries.set(i, Math.cos(Math.toRadians(i)));
            
        }
        
        
        sineSeries.setLabel("Sinus");
        cosineSeries.setLabel("Cosinus");
        
        Axis xAxis = lineModel.getAxis(AxisType.X);
        xAxis.setMin(0);
        xAxis.setMax(360);
        lineModel.setTitle("Sinus/Cosinus");
        lineModel.setZoom(true);
        lineModel.setLegendPosition("e");
        lineModel.addSeries(sineSeries);
        lineModel.addSeries(cosineSeries);
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }
    
}
