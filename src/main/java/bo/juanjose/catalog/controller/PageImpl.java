package bo.juanjose.catalog.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class PageImpl<T> implements Serializable {
    private Map<String, Object> info = new HashMap<>();
    private List<T> results;

    public PageImpl(Page<T> page){
        this.results = page.getContent();
        this.info.put("currentPage", page.getNumber());
        this.info.put("totalItems", page.getTotalElements());
        this.info.put("totalPages", page.getTotalPages());
    }

}
