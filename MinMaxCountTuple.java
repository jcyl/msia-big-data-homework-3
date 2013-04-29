
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;
public class MinMaxCountTuple implements Writable{

    private Integer min;
    private Integer max;
    private long count;

    public MinMaxCountTuple(){
	this.min=0;
	this.max=0;
	this.count=0;
    }

    public Integer getMin(){
	return min;
    }
    public void setMin(Integer min){
	this.min=min;
    }
    public Integer getMax(){
	return max;
    }
    public void setMax(Integer max){
	this.max=max;
    }
    public long getCount(){
	return count;
    }
    public void setCount(long count){
	this.count=count;
    }
    public void readFields(DataInput in) throws IOException {
	min = in.readInt();
	max = in.readInt();
	count = in.readLong();
    }

    public void write(DataOutput out) throws IOException {
	out.writeInt(min);
	out.writeInt(max);
	out.writeLong(count);
    }

    public String toString() {
	return min + "\t" + max + "\t" + count;
    }
}
