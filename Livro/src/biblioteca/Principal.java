package biblioteca;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author aluno
 */
public class Principal {

    public static void main(String[] args) {

        Livros livro1 = new Livros();
        livro1.setId(1);
        livro1.setTitulo("Por lugares incriveis\n");
        livro1.setAutor("Desconhecido");

        Livros livro2 = new Livros();
        livro2.setId(2);
        livro2.setTitulo("O Pequeno Principe\n");
        livro2.setAutor("Antoine de Saint-Exupery");
        
        
        LivroBD livroConexao = new LivroBD();
        livroConexao.conectarBD();
        livroConexao.criarTabela();

        livroConexao.create(livro1);
        livroConexao.create(livro2);
        
        System.out.println("" + livroConexao.listarTudo());
        System.out.println("" + livroConexao.searchById(2));
        
        System.out.println("====");
        livroConexao.delete(livro1);
        System.out.println("" + livroConexao.listarTudo());

        //crud atualizar
        livro2.setAutor("Pequeno");
        livroConexao.update(livro2);
    }
}
