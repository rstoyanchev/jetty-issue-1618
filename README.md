# jetty-issue-1618
https://github.com/eclipse/jetty.project/issues/1618

Run `Main.java` and then `curl -v http://localhost:8080/foo/vv%2F1234/add/2`.

Resulting output:
```
Processing REQUEST to /
Processing ASYNC to /
Completing async request
Processing REQUEST to /foo/vv%2F1234/add/2
Processing ASYNC to /foo/vv/1234/add/2
Completing async request
```

The ASYNC dispatch however should be to the encoded URI as per the Servlet spec:
> If the AsyncContext was initialized via the startAsync(ServletRequest, ServletResponse) and the request passed is an instance of HttpServletRequest , then the dispatch is to the URI returned by HttpServletRequest.getRequestURI()
