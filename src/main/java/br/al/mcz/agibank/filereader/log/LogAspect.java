package br.al.mcz.agibank.filereader.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* decodificar(..))")
    private void executarDecodificar() {}

    @Before("executarDecodificar()")
    private void antesDecodificar(JoinPoint joinPoint) {
        log.info("Convertendo dados: " + joinPoint.getArgs()[0].toString());
    }

    @AfterReturning(pointcut = "executarDecodificar()", returning = "entidade")
    private void depoisDecodificar(JoinPoint joinPoint, Object entidade) {
        log.info("Entidade convertida: " + entidade.toString());
    }


}
