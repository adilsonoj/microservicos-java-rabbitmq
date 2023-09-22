package com.oliver.backendapi.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.oliver.backendapi.facade.PagamentoFacade;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PagamentoResponseErroConsumer {
  @Autowired
  private PagamentoFacade pagamentoFacade;

  @RabbitListener(queues = { "pagamento-response-erro-queue" })
  public void receive(@Payload Message message) {
    log.info("Message " + message);

    String payload = String.valueOf(message.getPayload());

    pagamentoFacade.erroPagamento(payload);
  }

}
