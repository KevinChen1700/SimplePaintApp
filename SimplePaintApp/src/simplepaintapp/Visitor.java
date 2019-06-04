package simplepaintapp;

//Visitor interface
public interface Visitor {

    public void visitSave(VisitorSave saveFile);

    public void visitMove(Move move);

    public void visitResize(Resize resize);
}
