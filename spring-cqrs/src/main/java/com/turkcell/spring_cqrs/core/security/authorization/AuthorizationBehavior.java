package com.turkcell.spring_cqrs.core.security.authorization;

import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.spring_cqrs.core.exception.UnauthenticatedException;
import com.turkcell.spring_cqrs.core.exception.UnauthorizedException;
import com.turkcell.spring_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.spring_cqrs.core.mediator.pipeline.RequestHandlerDelegate;
import com.turkcell.spring_cqrs.core.security.context.UserContext;

@Component
@Order(10)
public class AuthorizationBehavior implements PipelineBehavior {

    private final UserContext userContext;

    public AuthorizationBehavior(UserContext userContext) {
        this.userContext = userContext;
    }

    @Override
    public boolean supports(Object request) {
        return request instanceof AuthorizableRequest;
    }

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {

        if (!userContext.isAuthenticated()) {
            throw new UnauthenticatedException("Giriş yapmalısın.");
        }

        AuthorizableRequest authorizableRequest = (AuthorizableRequest) request;

        List<String> requiredRoles = authorizableRequest.getRequiredRoles();

        if (requiredRoles != null && !requiredRoles.isEmpty()) {
            boolean hasRequiredRole = requiredRoles.stream()
                    .anyMatch(role -> userContext.hasRole(role));

            if (!hasRequiredRole) {
                throw new UnauthorizedException("Bu işlem için yetkin yok.");
            }
        }

        // Özel bir exception türü belirle.
        // Handlerda bu exceptionı eğer giriş yapılmamışsa 401, (UnauthenticatedException)
        // yapılmış ancak rol yetersiz ise 403 döndürecek şekilde (UnauthorizedException)
        // düzenle..
        
        return next.invoke(); // zincirdeki sonraki halkayı çağır..
    }
}

    // 3 adet çalışan pipeline yazalım. 
    //1. performans monitoring - Belirli süreyi aşan requestleri uyarı olarak yakala. 3000ms'i geçen requestler konsola kendi
    //2. Logging Behaviour - Tüm requestleri içindeki bilgi, dönen cevap nedir ayrı ayrı loglasın. (konsol)
    //3. Transaction Behavior -> Araştıralım (Bütünlük sağlama için) ve uygulamaya çalışalım.
