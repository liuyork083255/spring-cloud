package liu.york.client.one.consul;

import com.ecwid.consul.v1.kv.model.GetValue;
import com.google.gson.Gson;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContenderValue implements Serializable {

    private Integer limit;  // 上限值
    private List<String> holders = new ArrayList<String>();   // 持有者列表

    /**
     * 根据consul中获取的/.lock值来转换
     *
     * @param lockKeyContent
     * @return
     */
    public static ContenderValue parse(GetValue lockKeyContent) throws IOException {
        // 获取Value信息，decode BASE64
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] v = decoder.decodeBuffer(lockKeyContent.getValue());
        String lockKeyValueDecode = new String(v);
        // 根据json转换为ContenderValue对象
        Gson gson = new Gson();
        return gson.fromJson(lockKeyValueDecode, ContenderValue.class);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<String> getHolders() {
        return holders;
    }

    public void setHolders(List<String> holders) {
        this.holders = holders;
    }
}