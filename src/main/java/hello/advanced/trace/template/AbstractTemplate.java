package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;


/**
 *  템플릿 메서드 패턴
 *
 *  부모 클래스에 알고리즘 골격인 템플릿을 정의하고, 일부 변경되는 로직은 자식 클래스에 정의하는 것.
 *  자식 클래스가 알고리즘 전체 구조를 변경하지 않고, 특정 부분만 재정의할 수 있다.
 *  결국 상속과 오버라이딩을 통한 다형성
 *
 *  그러나, 상속을 받기 때문에, 부모 클래스에 의존관계가 생긴다.
 *  자식 클래스에서는 부모 클래스의 기능은 전혀 사용하지 않는데, 부모 클래스를 알아야하며, 부모 클래스를 수정하면 자식 클래스에도 영향을 줄 수 있다.
 *  익명 내부 클래스를 만들어야 하는 부분도 복잡..
 *
 */
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    protected AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);
            // 로직 호출
            T result = call();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
