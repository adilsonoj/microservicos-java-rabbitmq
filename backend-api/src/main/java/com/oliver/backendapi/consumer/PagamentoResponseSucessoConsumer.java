package com.oliver.backendapi.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.oliver.backendapi.facade.PagamentoFacade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PagamentoResponseSucessoConsumer {
  @Autowired
  private PagamentoFacade pagamentoFacade;

  @RabbitListener(queues = { "pagamento-response-sucesso-queue" })
  public void receive(@Payload Message message) {
    log.info("Message " + message);

    String payload = String.valueOf(message.getPayload());

    pagamentoFacade.sucessoPagamento(payload);
  }
}
