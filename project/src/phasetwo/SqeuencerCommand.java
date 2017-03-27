package phasetwo;

import java.io.IOException;
import java.util.logging.Level;

public class SqeuencerCommand implements Cammand   {
  warehouseSystem theSystem;
  String[] userInput;
  Sequencer currentSequencer;
  
  
  
  public SqeuencerCommand(warehouseSystem theSystem, String[] userInput) {
    this.theSystem = theSystem;
    this.userInput  = userInput;
    currentSequencer = theSystem.hrsystem.getSequencer(userInput[1]);
  }


  @Override
  public void execute() throws IOException {
    if (userInput[2].equals("ready")) { 
      sequencerReady(theSystem.hrsystem, userInput, currentSequencer);
    }else if(userInput[2].equals("scan")){
        SequencerScan(theSystem.orderManager, theSystem.hrsystem, userInput, currentSequencer);
    }else if(userInput[2].equals("rescan")){
      currentSequencer.rescan(theSystem.orderManager);
      theSystem.LOGGER.log(Level.FINER,"Sequencer "+ currentSequencer.getName() + " is about to rescan all the fascia");
        //sequencer rescan
    }else if(userInput[2].equals("finish")){
        //sequencer finish sequencing send all the item to loader
    }

  }

  private  void sequencerReady(Hrsystem hrsystemA, String[] userInput,
      Sequencer currSequencer) {
    if (hrsystemA.getSequencingid() == 0) {
      theSystem.LOGGER.log(Level.FINE,"not enough for Sequenceing" + "\n");
        } else {
          Integer sequencingId = hrsystemA.getSequencingid();
          currSequencer.ready(hrsystemA.getSequencingid());
          theSystem.LOGGER.log(Level.FINE, "Sequencer " + userInput[1] + " resive the picked ID of " + 
          hrsystemA.getSequencingid().toString());
        }
  }

  private  void SequencerScan(OrderManager orderManager, Hrsystem hrsystemA,
      String[] userInput, Sequencer currSequencer) {
    //sequencer scan item one by one 
    if (currSequencer.scan(userInput[3])==7 && currSequencer.compare(orderManager)){
      // if all 8 of them are correct, Sequencer will send them to loading 
      theSystem.LOGGER.log(Level.FINER, "Sequencer " + currSequencer.getName() + " scan all 8 Fascia");
        hrsystemA.addToloader(currSequencer.getid(),currSequencer.sequencing(orderManager));
        theSystem.LOGGER.log(Level.FINER, "Sequencer " + currSequencer.getName() + " sequencing all Fascia "
            + "and send them to loading");
          
    }else if (!currSequencer.compare(orderManager)){
      theSystem.LOGGER.log(Level.FINER, "Sequencer " + currSequencer.getName() + " scan" + userInput[3] );
      currSequencer.repick(orderManager);
      theSystem.LOGGER.log(Level.FINER, "Sequencer " + currSequencer.getName() + " find error send back all the Fasica with "
          + "picking ID of " + currSequencer.getid() );
      
    }else{
      theSystem.LOGGER.log(Level.FINER, "Sequencer " + currSequencer.getName() + " scan" + userInput[3] + 
          " and it is a correct one");
    }
  }

}