class Reader {
    static BufferedReader br;
    static InputStreamReader isr;
    static StringTokenizer tokenizer;
    static String buffer;
    
    public Reader(){
        isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);
    }
    
    public Reader(InputStream input){
        isr = new InputStreamReader(input);
        br = new BufferedReader(isr);
    }
    
    boolean hasNext() throws IOException{
        if(tokenizer.hasMoreTokens()) return true;
        else {
            buffer = br.readLine();
            if(buffer == null || buffer.length()==0) return false;
            else {
                tokenizer = new StringTokenizer(buffer);
                return true;
            }
        }
    }
       
    String next() throws IOException{
        while(!tokenizer.hasMoreTokens()){
            tokenizer = new StringTokenizer(br.readLine());
        }
        return tokenizer.nextToken();
    }
    
    public int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
    
    public double nextDouble() throws IOException{
        return Double.parseDouble(next());
    }
    
}
