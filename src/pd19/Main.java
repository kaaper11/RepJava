package pd19;

import pd19.dto.BookDto;
import pd19.dto.CreateLoanRequest;
import pd19.dto.MemberDto;
import pd19.dto.ReturnBookRequest;
import pd19.entity.Book;
import pd19.entity.Member;
import pd19.repository.BookRepository;
import pd19.repository.LoanRepository;
import pd19.repository.MemberRepository;
import pd19.service.ServClass.BookServiceImpl;
import pd19.service.ServClass.LoanServiceImpl;
import pd19.service.ServClass.MemberServiceImpl;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();
        LoanRepository loanRepository = new LoanRepository();

        BookServiceImpl bookService = new BookServiceImpl(bookRepository);
        MemberServiceImpl memberService = new MemberServiceImpl(memberRepository);
        LoanServiceImpl loanService = new LoanServiceImpl(loanRepository, bookRepository, memberRepository);

        BookDto book1 = new BookDto("123", "title", "author", 2020, 1);
        BookDto book2 = new BookDto("1234", "title1", "author1", 2010, 2);
        BookDto book3 = new BookDto("1235", "title2", "author", 2015, 3);

        MemberDto member1 = new MemberDto("name", "email@wp.pl");
        MemberDto member2 = new MemberDto("name1", "email@gmail.pl");

        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);

        memberService.register(member1);
        memberService.register(member2);

        CreateLoanRequest loanRequest = new CreateLoanRequest(0, 0);

        System.out.println(bookService.findByLsbn("123"));
        System.out.println(bookService.findAvailable());
        System.out.println(bookService.search("author"));

        System.out.println(memberService.findById(0));

        loanService.borrow(loanRequest);
        System.out.println(loanService.getActiveLoans(0));

        ReturnBookRequest returnBookRequest = new ReturnBookRequest(0);
        loanService.returnBook(returnBookRequest);
        System.out.println(loanService.getActiveLoans(0));
    }
}
