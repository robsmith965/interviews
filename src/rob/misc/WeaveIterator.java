package rob.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Google phone screen 3/9/15
 * @author rrobsm
 *
 */

public class WeaveIterator implements Iterator<Integer> {

private List<Integer> ints;
private int pointer;

public WeaveIterator(Iterator ... iterators ) {
 ints = new ArrayList<Integer>();
 pointer = 0;

 for( Iterator i : iterators ) {
  if( i.hasNext() ) {
   ints.add((Integer) i.next());
  }
 }
}

public boolean hasNext() {
 if( pointer < ints.size() - 1 ) {
  return true;
 } else {
  return false;
 }
}

public Integer next() {
 if( this.hasNext() ) {
  int val = ints.get(pointer);
  pointer++;
  return val;
 }
 return null;
}

/*
 int size = 0;
 for( Iterator i : iterators ) {
  size += i.size();
 }
 for( int i = 0; i < size; i++ ) {
  for( Iterator i : iterators ) {
   if( i.hasNext() ) {
    ints.add(i.next());
   }
 }
*/

@Override
public void remove() {
    // TODO Auto-generated method stub
    
}

}
