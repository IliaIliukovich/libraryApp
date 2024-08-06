package com.telran.libraryapp.scheduler;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ScheduledTask {

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void scheduledTaskOne(){
        log.info("Doing task at fixedRate ... " + new Date());
    }

    @Async
    @Scheduled(cron = "*/5 * * * * *")
    public void scheduledTaskTwo(){
        log.info("Doing task with cron ... " + new Date());
    }

    @Scheduled(cron = "1 * * * * *")
    public void getRss(){
        try {
            URL feedSource = new URL("http://rss.accuweather.com/rss/liveweather_rss.asp?locCode=Berlin");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            log.info("Title: " + feed.getTitle());
            log.info("Description: " + feed.getDescription());
            List<SyndEntry> entries = feed.getEntries();
            entries.forEach(syndEntry -> {
                log.info(syndEntry.getTitle());
                log.info(syndEntry.getDescription().toString());
            });
        } catch (FeedException | IOException e) {
            log.error(e.getMessage());
        }
    }


}
