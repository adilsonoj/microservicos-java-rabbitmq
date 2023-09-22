package com.oliver.backendapi.facade;

import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oliver.backendapi.dto.PagamentoDTO;
import com.oliver.backendapi.producer.PagamentoRequestProducer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PagamentoFacade {

  @Autowired
  private PagamentoRequestProducer producer;

  public String solicitarPagamento(PagamentoDTO request) {
    try {
      producer.integrar(request);
    } catch (JsonProcessingException | AmqpException e) {
      return "Ocorreu um erro ao integrar " + e.getMessage();
    }
    return "Pagamento aguardando confirmação...";
  }

  public void erroPagamento(String payload) {
    log.error("=== RESPOSTA ERRO " + payload);
  }

  public void sucessoPagamento(String payload) {
    log.info("=== RESPOSTA SUCESSO " + payload);
  }

}
