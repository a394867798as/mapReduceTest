package com.phoneData;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context)
            throws IOException, InterruptedException {
        long sumUpFlow = 0;
        long sumDownFlow = 0;
        System.out.println(values);
        for (FlowBean flowBean: values) {
            sumUpFlow+=flowBean.getUpflow();
            sumDownFlow+=flowBean.getDownflow();
        }
        FlowBean v=new FlowBean(sumUpFlow,sumDownFlow);
        context.write(key, v);
    }
}
