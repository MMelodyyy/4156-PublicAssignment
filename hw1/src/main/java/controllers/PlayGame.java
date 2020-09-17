package controllers;

import io.javalin.Javalin;
import models.GameBoard;

import java.io.IOException;
import java.util.Queue;
import org.eclipse.jetty.websocket.api.Session;

import com.google.gson.Gson;

class PlayGame {

  private static final int PORT_NUMBER = 8080;
  
  private static GameBoard gameboard;
  

  private static Javalin app;
  
  public static Gson gson;

  /** Main method of the application.
   * @param args Command line arguments
   */
  public static void main(final String[] args) {	

    app = Javalin.create(config -> {
      config.addStaticFiles("/public");
    }).start(PORT_NUMBER);

    // Test Echo Server
    app.post("/echo", ctx -> {
      ctx.result(ctx.body());
    });
    
    // Hello Test
    app.get("/hello", ctx -> {
        ctx.result("Hello class");
      });
    
    // Redirect to new game
    app.get("/", ctx -> {
        ctx.redirect("/newgame");
      });

    // Start a new game
    app.get("/newgame", ctx -> {
        ctx.redirect("/tictactoe.html");
      });
     
    app.post("/startgame", ctx -> {
    	String type = null;
    	try {
    		type = ctx.formParam("type");
    		gameboard = new GameBoard(type.charAt(0)); // set Player1
    		Gson gson = new Gson();
    		String boardjson = gson.toJson(gameboard);
    		ctx.json(boardjson);	
    		
    	}catch(Exception ex) {
    		ctx.result("unknow error when start new game").status(404);
    	} 	
      
      });
    
    // Player2 join game
    app.get("/joingame", ctx ->{
    	try {
    		gameboard.p2Join();
    		Gson gson = new Gson();
    		String boardjson = gson.toJson(gameboard);
    		ctx.redirect("/tictactoe.html?p=2");
    		sendGameBoardToAllPlayers(boardjson); 		 		
    	}catch(Exception ex) {
    		ctx.result("Can not join p2").status(404);	
    	}
    });
    
    
    /**
     * Please add your end points here.
     * 
     * 
     * 
     * 
     * Please add your end points here.
     * 
     * 
     * 
     * 
     * Please add your end points here.
     * 
     * 
     * 
     * 
     * Please add your end points here.
     * 
     */

    // Web sockets - DO NOT DELETE or CHANGE
    app.ws("/gameboard", new UiWebSocket());
  }



public static void setGameboard(GameBoard gameboard) {
	PlayGame.gameboard = gameboard;
}

/** Send message to all players.
   * @param gameBoardJson Gameboard JSON
   * @throws IOException Websocket message send IO Exception
   */
  private static void sendGameBoardToAllPlayers(final String gameBoardJson) {
    Queue<Session> sessions = UiWebSocket.getSessions();
    for (Session sessionPlayer : sessions) {
      try {
        sessionPlayer.getRemote().sendString(gameBoardJson);
      } catch (IOException e) {
        // Add logger here
      }
    }
  }

  public static void stop() {
    app.stop();
  }
}
