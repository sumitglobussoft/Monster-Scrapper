package com.monsterscrapper.entity;

import com.monsterscrapper.entity.JobDetails;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-01-19T12:57:05")
@StaticMetamodel(CrawledJobs.class)
public class CrawledJobs_ { 

    public static volatile SingularAttribute<CrawledJobs, String> postedDate;
    public static volatile SingularAttribute<CrawledJobs, Integer> jobId;
    public static volatile SingularAttribute<CrawledJobs, String> jobUrl;
    public static volatile SingularAttribute<CrawledJobs, String> keyword;
    public static volatile SingularAttribute<CrawledJobs, Integer> isCrawled;
    public static volatile CollectionAttribute<CrawledJobs, JobDetails> jobDetailsCollection;
    public static volatile SingularAttribute<CrawledJobs, String> country;

}