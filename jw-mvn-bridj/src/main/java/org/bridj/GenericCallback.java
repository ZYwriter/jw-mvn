/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bridj;

/**
 * Generic Java callback to be called from C.
 * @author ochafik
 */
public interface GenericCallback {
    public abstract Object apply(Object... args);
}
