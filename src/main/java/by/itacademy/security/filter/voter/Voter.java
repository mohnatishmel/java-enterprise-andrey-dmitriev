package by.itacademy.security.filter.voter;

import javax.servlet.http.HttpServletRequest;

public interface Voter {
    boolean vote(HttpServletRequest request);
}
