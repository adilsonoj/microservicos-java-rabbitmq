package com.oliver.backendworker.consumer;

import java.util.Random;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.oliver.backendworker.producer.PagamentoErroProducer;
import com.oliver.backendworker.producer.PagamentoSucessoProducer;

@Component
public class PagamentoRequestConsumer {

  @Autowired
  private PagamentoErroProducer erroProducer;
  @Autowired
  private PagamentoSucessoProducer sucessoProducer;

  @RabbitListener(queues = { "pagamento-request-queue" })
  public void receberMensagem(@Payload Message message) {
    System.out.println(message);
    if (new Random().nextBoolean()) {
      sucessoProducer.gerarResposta("Pagamento com sucesso" + message);
    } else {
      erroProducer.gerarResposta("Pagamento com erro" + message);
    }
  }
}
