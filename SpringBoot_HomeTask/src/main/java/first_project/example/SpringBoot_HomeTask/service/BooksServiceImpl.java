package first_project.example.SpringBoot_HomeTask.service;

import first_project.example.SpringBoot_HomeTask.dao.BooksDao;
import first_project.example.SpringBoot_HomeTask.domain.Books;
import org.springframework.stereotype.Service;

//@Service
public class BooksServiceImpl implements BooksService{
    private BooksDao dao;

    /*public void setDao(BooksDao dao) {
        this.dao = dao;
    }
    public BooksServiceImpl() {

    }*/

    public BooksServiceImpl( BooksDao dao ) {
        this.dao = dao;
    }

    @Override
    public Books getAllBooksService() {
        return dao.getAllBooks();
    }

    @Override
    public void addBookService(String name) {
        dao.addBook(name);
    }

}
