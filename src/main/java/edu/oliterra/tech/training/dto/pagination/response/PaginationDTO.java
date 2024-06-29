package edu.oliterra.tech.training.dto.pagination.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDTO {

    private int offset;
    private int pageSize;
    private int totalPages;
    private boolean first;
    private boolean last;
}
