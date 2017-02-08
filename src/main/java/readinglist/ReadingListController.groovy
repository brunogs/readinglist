package readinglist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

@Controller
@RequestMapping('/')
class ReadingListController {

    ReadingListRepository readingListRepository

    @Autowired
    ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository
    }

    @RequestMapping(value = '/{reader}', method = GET)
    String readersBooks(@PathVariable('reader') String reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader)
        if (readingList) {
            model.addAttribute('books', readingList)
        }
        'readingList'
    }

    @RequestMapping(value = '/{reader}', method = POST)
    String addToReadingList(@PathVariable('reader') String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        'redirect:/{reader}';
    }
}
