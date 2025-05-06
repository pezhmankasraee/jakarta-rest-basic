package com.pezhmankasraee.jakartarestbasic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BulkStudentRequest {

    private List<Long> ids;
}
