package com.opstty.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

public class DistrictCountMapper extends Mapper<Object, Text, Text, IntWritable> {
    private final static IntWritable nul = new IntWritable(-1);
    private Text line = new Text();

    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString(),"\n");

        //skip header row


        while (itr.hasMoreTokens()) {
            line.set(itr.nextToken());
            String district = line.toString().split(";")[1];
            context.write(new Text(district), nul);
            //context.write(new Text(line), nul);
        }
    }
}