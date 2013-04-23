/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.UUID;

/**
 *
 * @author Xi
 */
public class UUIDGenerater {
    
    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }
    
    // For unit test
    public static void main(String args[]) {
        System.out.println(generateUniqueId());
    }
}
