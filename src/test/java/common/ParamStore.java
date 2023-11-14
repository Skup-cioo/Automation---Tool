package common;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ParamStore {
    private final Map<String, Object> paramsStore;
    private final Map<String, Boolean> isParamUsed;


    public ParamStore() {
        this.paramsStore = new HashMap<>();
        this.isParamUsed = new HashMap<>();
    }

    public void setParam(String key, Object value) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("Param name must not be null nor empty");
        } else if (value == null) {
            throw new IllegalArgumentException("Param value must not be null");
        } else {
            this.paramsStore.put(key, value);
            if (this.isParamUsed != null) {
                this.isParamUsed.put(key, false);
            }
        }
    }

    public boolean hasParam(String key) {
        return this.paramsStore.containsKey(key);
    }

    public Object getParam(String key) {
        if (StringUtils.isEmpty(key)) {
            throw new IllegalArgumentException("Param name must not be null nor empty");
        } else {
            return this.paramsStore.get(key);
        }
    }
}
