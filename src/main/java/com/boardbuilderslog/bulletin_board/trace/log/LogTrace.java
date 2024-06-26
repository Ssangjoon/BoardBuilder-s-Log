package com.boardbuilderslog.bulletin_board.trace.log;

import com.boardbuilderslog.bulletin_board.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
}
