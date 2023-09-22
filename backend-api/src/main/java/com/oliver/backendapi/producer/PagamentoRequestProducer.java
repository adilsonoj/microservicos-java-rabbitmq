package com.oliver.backendapi.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oliver.backendapi.dto.PagamentoDTO;

@Component
public class PagamentoRequestProducer {
  @Autowired
  private AmqpTemplate amqpTemplate;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public void integrar(PagamentoDTO pagamento) throws JsonProcessingException, AmqpException {
    amqpTemplate.convertAndSend(
        "pagamento-request-exchange",
        "pagamento-request-rout-key",
        objectMapper.writeValueAsString(pagamento));
  }

}
