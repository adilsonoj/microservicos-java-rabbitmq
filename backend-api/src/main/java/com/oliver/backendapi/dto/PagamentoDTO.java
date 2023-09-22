package com.oliver.backendapi.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PagamentoDTO {
  private String numeroPedido;
  private BigDecimal valor;
  private String produto;
}
