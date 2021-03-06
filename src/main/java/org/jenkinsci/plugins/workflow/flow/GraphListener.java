/*
 * The MIT License
 *
 * Copyright (c) 2013-2014, CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkinsci.plugins.workflow.flow;

import hudson.Extension;
import hudson.ExtensionPoint;
import org.jenkinsci.plugins.workflow.graph.FlowNode;

/**
 * {@code GraphListener}s can be used in two different ways: either as an {@link Extension}, which will have its
 * {@link #onNewHead(FlowNode)} fired for every {@link FlowExecution}, or by instantiation and being passed to
 * {@link FlowExecution#addListener(GraphListener)}, in which case only events for that specific {@link FlowExecution}
 * will be fired.
 */
public interface GraphListener extends ExtensionPoint {
    /**
     * {@link FlowExecution} should batch up changes to a group and call this once,
     * as opposed to call this for every new node added.
     *
     * One of the use cases of this listener is to persist the state of {@link FlowExecution}.
     */
    void onNewHead(FlowNode node);

    /**
     * Listener which should be notified of events immediately as they occur.
     * You must be very careful not to acquire locks or block.
     * If you do not implement this marker interface, you will receive notifications in batched deliveries.
     */
    interface Synchronous extends GraphListener {}

}
